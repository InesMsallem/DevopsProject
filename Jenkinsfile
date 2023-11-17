pipeline {
    agent any
    environment {
    IMAGE_TAG = "${env.BUILD_NUMBER}"
    }
    // tools{
    //     maven 'M2_HOME'
    // }
    stages {
        stage('Chekout GIT'){
            steps {
                echo 'Pulling...'
                git branch: 'yassine' ,
                url : 'https://github.com/InesMsallem/DevopsProject.git'
            }
        }
        stage('MVN CLEAN'){
            steps{
                sh 'mvn clean';
            }
        }
        stage('MVN COMPILE'){
            steps{
                sh 'mvn compile';
            }
        }
        stage('MVN TEST'){
            steps{
                sh 'mvn clean test';
        
            }
        }
        stage('JaCoCo Reports') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true';
            }
        }
        stage("Build") {
            steps {
                sh 'mvn install -DskipTests=true'
            }
        }
        stage('MVN NEXUS') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true';
            }
        }
        stage('Build backend docker image') {
            steps {
                sh 'docker build -t ye55ine/devops:$IMAGE_TAG .'
            }
        }
        // stage('Push images to Dockerhub') {
        //     steps {
        //         script {
        //             sh 'docker login --username ye55ine --password-stdin < ./pw.txt'
        //             sh 'docker push ye55ine/devops:$IMAGE_TAG'
        //         }
        //     }
        // }
        stage('Run Docker Compose') {
            steps {
                script {
                    sh 'docker compose down'
                    sh 'docker compose up -d --build'
                }
            }
        }
    }
}