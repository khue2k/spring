package com.example.IdentityService.test;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppMonitor {

    // Interface cho Psapi.dll (để lấy tên process)
    public interface Psapi extends StdCallLibrary {
        Psapi INSTANCE = Native.load("psapi", Psapi.class);

        boolean GetModuleBaseNameW(HANDLE hProcess, Pointer hModule, char[] lpBaseName, int nSize);
    }

    // Lấy process ID từ HWND
    private static int getProcessIdFromWindow(HWND hWnd) {
        IntByReference pidRef = new IntByReference();
        User32.INSTANCE.GetWindowThreadProcessId(hWnd, pidRef);
        return pidRef.getValue();
    }

    // Lấy tên process (ví dụ: chrome.exe, notepad.exe)
    private static String getProcessName(int pid) {
        HANDLE hProcess = Kernel32.INSTANCE.OpenProcess(
                0x0400 | 0x0010,  // PROCESS_QUERY_INFORMATION | PROCESS_VM_READ
                false,
                pid
        );

        if (hProcess == null) {
            return "Unknown";
        }

        try {
            char[] name = new char[1024];
            if (Psapi.INSTANCE.GetModuleBaseNameW(hProcess, null, name, name.length)) {
                return Native.toString(name).trim();
            }
        } finally {
            Kernel32.INSTANCE.CloseHandle(hProcess);
        }
        return "Unknown";
    }

    public static void main(String[] args) {


        String s ="";
        // Lấy HWND của chính ứng dụng Java hiện tại (nếu có GUI)
        // Nếu là console app → có thể bỏ qua hoặc dùng JFrame để lấy
        HWND myWindow = User32.INSTANCE.GetForegroundWindow(); // tạm thời lấy lúc khởi động
        // Hoặc nếu bạn dùng Swing/JavaFX: frame.getHWnd() (cần implement)

        int myPid = (int) ProcessHandle.current().pid();  // Java 9+
        // Hoặc: Kernel32.INSTANCE.GetCurrentProcessId();

        System.out.println("PID của app bạn: " + myPid);
        System.out.println("Bắt đầu theo dõi... Nhấn Ctrl+C để dừng.");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            HWND foreground = User32.INSTANCE.GetForegroundWindow();

            if (foreground != null) {
                int pid = getProcessIdFromWindow(foreground);
                String processName = getProcessName(pid);

                // So sánh PID để tránh báo nhầm chính app bạn
                if (pid != myPid && !processName.isEmpty() && !processName.equals("Unknown")) {
                    // Lấy tiêu đề cửa sổ (tùy chọn)
                    char[] title = new char[512];
                    User32.INSTANCE.GetWindowText(foreground, title, title.length);
                    String windowTitle = Native.toString(title).trim();

                    System.out.printf("[%s] Người dùng chuyển sang app khác: %s (PID: %d) - Tiêu đề: %s%n",
                            java.time.LocalTime.now(), processName, pid, windowTitle);

                    // Ở đây bạn có thể:
                    // - Ghi log vào file
                    // - Gửi cảnh báo (email, socket...)
                    // - Pause chức năng app của bạn
                    // - Hoặc thậm chí chặn bằng cách SetForegroundWindow(myWindow)
                }
            }
        }, 0, 800, TimeUnit.MILLISECONDS);  // Kiểm tra mỗi ~0.8 giây

        // Giữ chương trình chạy (console app)
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            scheduler.shutdown();
        }
    }
}