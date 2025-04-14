pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Prepare Env') {
      steps {
        sh 'cp /home/ubuntu/path/backoffice/.env .'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }
  }
}