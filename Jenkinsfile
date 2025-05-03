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
        sh '''
          bash -c "
            set -a
            . $WORKSPACE/.env
            set +a
            ./gradlew build
          "
        '''
      }
    }

    stage('Docker Build & Deploy') {
      steps {
        script {
           sh '''
             bash -c "
                docker stop ${CONTAINER_NAME} || true
                docker rm ${CONTAINER_NAME} || true
                docker build -t ${IMAGE_NAME} .
                docker run -d --name ${CONTAINER_NAME} --network my-network --env-file .env -p 8081:8080 ${IMAGE_NAME}
              "
          '''
        }
      }
    }
  }
}
