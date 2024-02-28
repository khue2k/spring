pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'cat /home/huhu.txt'
                sh 'echo DuongXuanKhue'
                echo "Build number is ${currentBuild.number}"
                echo "Build result is ${currentBuild.result}"
                echo "Build displayName is ${currentBuild.displayName}"
            }
        }
    }
}