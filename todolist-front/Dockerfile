FROM node:18-alpine AS base

WORKDIR /front-app
COPY package*.json yarn.lock ./
RUN yarn install --pure-lockfile

FROM base AS builder
WORKDIR /front-app
COPY . .
COPY --from=base /front-app/node_modules ./node_modules
ARG NEXT_PUBLIC_URL
ARG NEXT_PUBLIC_URL_SSR
RUN yarn build

FROM base AS runner
WORKDIR /front-app

COPY --from=builder /front-app/next.config.js ./
COPY --from=builder /front-app/public ./public
COPY --from=builder /front-app/.next ./.next
COPY --from=builder /front-app/node_modules ./node_modules
COPY --from=builder /front-app/package.json ./package.json

EXPOSE 3000

CMD ["yarn", "start"]
