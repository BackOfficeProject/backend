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
          set -a
          . .env
          set +a
          ./gradlew clean build
        '''
      }
    }
  }
}