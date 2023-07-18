FROM node:18-alpine AS builder

WORKDIR /front-app

COPY package*.json yarn.lock ./

RUN yarn install --pure-lockfile

COPY . .

RUN yarn build

EXPOSE 3000

CMD ["yarn", "start"]
