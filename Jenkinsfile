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

    stage('Prepare Env') {
      steps {
        sh '''
          if [ -f /env/.env ]; then
            cp /env/.env .
          else
            echo "ERROR: .env file not found at /env/.env"
            exit 1
          fi
        '''
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
          sh "docker run -d --name $CONTAINER_NAME --env-file .env -p 8080:8080 $IMAGE_NAME"
        }
      }
    }
  }
}
