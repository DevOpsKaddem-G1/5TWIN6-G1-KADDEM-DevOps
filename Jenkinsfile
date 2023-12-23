pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
     environment {
        imageName = ""
    }
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling... '
                git branch: 'Aziz-ben-hmida-Contrat', credentialsId: 'github', url: 'https://github.com/DevOpsKaddem-G1/5TWIN6-G1-KADDEM-DevOps.git'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('increment version') {
            steps {
                script {
                    echo 'Incrementing app version ...'
                    
                    // Increment the version using the Maven Versions Plugin
                    sh 'mvn versions:set -DnewVersion=1.1.0-SNAPSHOT'  // Update the new version as needed

                    // Read the updated version from the modified pom.xml
                    def updatedVersion = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def newVersion = updatedVersion[0][1]

                    // Set the image name with the updated version and Jenkins build number
                    imageName = "${newVersion}-${BUILD_NUMBER}"
                    echo "Updated version: ${newVersion}"
                    echo "Image name: ${imageName}"
                }
            }
        }
        stage('Maven Install') {
            steps {
                sh 'mvn install'
            }
        }        
        stage('Tests - JUnit/Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build package') {
            steps {
                sh 'mvn package'
            }
        }
        
        stage('Delay for 10 s') {
            steps {
                script {
                    sleep time: 10, unit: 'SECONDS'
                }
            }
        }
        stage("Sonar Quality Check") {
            steps {
                script {
                    withSonarQubeEnv(installationName: 'sonar-8', credentialsId: 'jenkins-sonar-token') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }    
        stage('Build Docker Image') {
            steps {
                script {
                   def dockerImage = docker.build("aziz/kaddem:${imageName}")
                }
            }
        }
    }
}
