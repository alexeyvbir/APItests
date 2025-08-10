pipeline {
    agent any            // Агент: где выполнять pipeline (any – на любом доступном узле, в нашем случае на самом Jenkins)
    tools {
        maven "maven 3.8.1"   // Указываем установленный Maven (имя должно совпадать с настроенным в Global Tools)
    }
    stages {
        stage('Build') {
            steps {
                echo 'Starting build...'
                // Сборка проекта (компиляция и упаковка без запуска тестов):
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Запускаем тесты Maven (TestNG + RestAssured):
                sh 'mvn clean test'
            }
            // Мы добавим блок post чуть позже для публикации отчёта Allure
        }
        // stage('Deploy') будет добавлен после настройки тестов и отчетов
    }
}