cd ../spring-social-strava
./gradlew clean build
cp build/distributions/spring-social-strava-1.0.0.BUILD-SNAPSHOT-dist.zip ../sputnik/libs/
cd ../sputnik/
rm libs/*.jar
unzip libs/spring-social-strava-1.0.0.BUILD-SNAPSHOT-dist.zip
rm libs/spring-social-strava-1.0.0.BUILD-SNAPSHOT-dist.zip
cp spring-social-strava-1.0.0.BUILD-SNAPSHOT/libs/*.jar libs/
rm -rf spring-social-strava-1.0.0.BUILD-SNAPSHOT/