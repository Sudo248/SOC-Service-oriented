set -e

# Wait for MySQL to be ready
./wait-db.sh db-service:3306 -t 30

# Start the Java application
java -jar $APP