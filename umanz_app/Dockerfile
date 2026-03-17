# Build stage
FROM node:20-slim AS builder
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Run stage
FROM node:20-slim
WORKDIR /app

COPY --from=builder /app/.output ./.output
# We might need package.json for some runtime deps if not bundled
# But Nuxt .output is usually self-contained for Nitro

ENV HOST=0.0.0.0
ENV PORT=3000
ENV NODE_ENV=production

RUN npm ci
RUN npx playwright install --with-deps chromium

EXPOSE 3000
CMD ["node", ".output/server/index.mjs"]
