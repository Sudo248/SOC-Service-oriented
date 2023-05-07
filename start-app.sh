set -e

# Wait for MySQL to be ready
./wait-db.sh db-service:3306 -t 50

# Start the Java application
java -jar $APP