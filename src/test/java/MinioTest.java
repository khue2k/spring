import io.minio.MinioClient;

public class MinioTest {
    public static void main(String[] args) {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint("http://localhost:9000")
                    .credentials("khue1234", "khue1234").build();
//            minioClient.deleteBucketEncryption();
        } catch (Exception e) {

        }
    }
}
