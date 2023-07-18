FROM node:18-alpine AS builder

WORKDIR /front-app

COPY package*.json yarn.lock ./

RUN yarn install --pure-lockfile

ENV NEXT_PUBLIC_URL=http://localhost:8080

COPY . .

RUN yarn build

EXPOSE 3000

CMD ["yarn", "start"]
