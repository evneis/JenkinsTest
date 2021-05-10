pipeline {
    agent any
    stages {
        stage('Test')
            agent{
                docker{
                    image 'maven:3.5.4'
                }
            }
            steps{
                sh 'mvn clean install'
                script{
                    try{
                        sh 'mvn test -B'
                    } catch(Exception e) {
                        step([$class: 'JUnitResultArchiver', testResults: './target/surefire-reports/TEST.xml'])
                        throw e
                    }
                }
            }
        }
        stage('Package') {
            agent{
                docker{
                    image 'maven:3.5.4' 
                }
            }
            steps {
                sh 'mvn clean install'
                sh 'mvn package' 
            }
        }

        stage('Build') {
            agent{ dockerfile true}
            steps {
                sh 'docker build -t jenkinstest .'
            }
        }
        stage('Deploy'){
            agent{ dockerfile true}
            steps{
                sh 'docker run -p 5000:5000 jenkinstest'
            }
        }
    }
}