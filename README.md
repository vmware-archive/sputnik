# Sputnik

## Development

### Local Development Environment Setup

- Install Java 1.8.
- Run `brew update`
- Run `brew install node`
- Run `npm install`
- Run `npm install -g grunt-cli`

- Install bower : `npm install bower`.
- Install karma: `npm install karma-jasmine@2_0 --save-dev`.

- Update application.yml with your postgres information.
- Run `migrations.sql in test/resources`.

- Run `karma start &` then `karma run` to run jasmine tests
- Run `./gradlew build` then `./gradlew bootRun` to run java tests and to start the server locally.

## Pivotal Web Services (PWS) App Hosting

The following document assumes Pivotal Web Services will be used to host the app. 

### Account Setup

Visit [PWS](http://run.pivotal.io/) to set up an account and create an organization to host the app. Login to your 
account from the command line by running `cf login -a api.run.pivotal.io`.

## Configuration

The directions below are intended for deploying *Sputnik* on [Pivotal Web Services](https://run.pivotal.io/).

### Environment Variable setup

Run `cp .env.example .env` from the proect root directory and fill in the environment variables as described below. Once
you are done, run `source .env` to set the environment variables on your local machine. Each environment variable may
be set on PWS by running `cf set-env APP NAME VALUE`.
 
### Base Url

The base url of the app must be set as `baseUrl`. 

### Timezone

Your timezone must be set in `application.properties` (ex: `timezone: America/Denver`).

### Strava

The app requires a Client ID (`stravaClientId`) and a Client Secret (`stravaClientSecret`). To obtain these visit the 
(Strava API page)[http://www.strava.com/developers] and click on **Create your Application**. The website and 
callback domain should be the same as your `base url`.

### Postgresql

Enable the *ElephantSQL* Service in the Services Marketplace and bind the service to your app. 

### SendGrid

Enable the *SendGrid* Service in the Services Marketplace and bind the service to your app. To use the *SendGrid* 
account locally, you must set the `VCAP_SERVICES` environment variable. Copy the `VCAP_SERVICES` from the 
*Env Variables* section of your app on the PWS console. Set your `emailFromAddress` in `application.properties`.

### Google Maps

Visit the [Google developer page](https://developers.google.com/maps/documentation/staticmaps/#api_key) and follow the 
instructions to get a Google Maps API key (`MAPS_API_KEY`).

### Stripe

Visit [Stripe](https://dashboard.stripe.com/register), sign up and retrieve your *Secret Key* (`stripeApiKey`) and 
*Publishable Key* (`STRIPE_PUBLIC_KEY`) as shown in the [API Keys section](https://dashboard.stripe.com/account/apikeys).
Set up your receipt email information in your [Stripe dashboard](https://dashboard.stripe.com/account/emails). You must
check *Email customers for successful payments* for your donors to receive receipts for their donations.

### System User

Once your app is deployed to PWS, log in first with the account that created the application. This account's credentials
will be used to make anonymous calls to the Strava API. Set the `systemUserId` environment variable to the `userid` of 
this user in the `users` table.

### Deployment

Deploy your app to [PWS](https://run.pivotal.io/) using `./gradlew clean build deploy`. Connect to the ElephantSQL 
database on PWS and run migrations from `src/test/resources/migration.sql` to set up your database.

