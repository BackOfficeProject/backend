pipeline {
  agent any

  environment {
    IMAGE_NAME = "backoffice-app"
    CONTAINER_NAME = "backoffice-container"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('Docker Build & Deploy') {
      steps {
        script {
          sh "docker stop $CONTAINER_NAME || true"
          sh "docker rm $CONTAINER_NAME || true"
          sh "docker build -t $IMAGE_NAME ."
          sh "docker run -d --name $CONTAINER_NAME -p 8080:8080 $IMAGE_NAME"
        }
      }
    }
  }
}
