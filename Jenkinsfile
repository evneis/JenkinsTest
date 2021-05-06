pipeline {
    agent any
    stages {
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
        stage('Test') {
            agent{
                docker{
                    image 'maven:3.5.4'
                }
            }
            steps{
                try{
                    sh 'mvn test -B'
                } catch(err) {
                    step([$class: 'JUnitResultArchiver', testResults: './target/surefire-reports/TEST.xml'])
                    throw err
                }
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