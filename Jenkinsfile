pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling latest code from GitHub...'
                git branch: 'main',
                    url: 'https://github.com/Jamneshmaniya15/Capstone-Project.git'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Compiling the Maven project...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running all test cases...'
                bat 'mvn test'
            }
        }

        stage('Publish Report') {
            steps {
                echo 'Publishing Extent HTML Report...'
                publishHTML([
                    allowMissing:          false,
                    alwaysLinkToLastBuild: true,
                    keepAll:               true,
                    reportDir:             'reports',
                    reportFiles:           'ExtentReport.html',
                    reportName:            'Extent Report'
                ])
            }
        }

    }

    post {
        success {
            echo '✅ All tests PASSED!'
            mail to:      'jammymaniya10@gmail.com',
                 subject:  "✅ PASSED - Build #${env.BUILD_NUMBER}",
                 body:     "All tests passed!\nReport: ${env.BUILD_URL}"
        }
        failure {
            echo '❌ Tests FAILED!'
            mail to:      'jammymaniya10@gmail.com',
                 subject:  "❌ FAILED - Build #${env.BUILD_NUMBER}",
                 body:     "Tests failed!\nCheck: ${env.BUILD_URL}console"
        }
    }
}pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling latest code from GitHub...'
                git branch: 'main',
                    url: 'https://github.com/Jamneshmaniya15/Capstone-Project.git'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Compiling the Maven project...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running all test cases...'
                bat 'mvn test'
            }
        }

        stage('Publish Report') {
            steps {
                echo 'Publishing Extent HTML Report...'
                publishHTML([
                    allowMissing:          false,
                    alwaysLinkToLastBuild: true,
                    keepAll:               true,
                    reportDir:             'reports',
                    reportFiles:           'ExtentReport.html',
                    reportName:            'Extent Report'
                ])
            }
        }

    }

    post {
        success {
            echo '✅ All tests PASSED!'
            mail to:      'jammymaniya10@gmail.com',
                 subject:  "✅ PASSED - Build #${env.BUILD_NUMBER}",
                 body:     "All tests passed!\nReport: ${env.BUILD_URL}"
        }
        failure {
            echo '❌ Tests FAILED!'
            mail to:      'jammymaniya10@gmail.com',
                 subject:  "❌ FAILED - Build #${env.BUILD_NUMBER}",
                 body:     "Tests failed!\nCheck: ${env.BUILD_URL}console"
        }
    }
}