FROM node:20-alpine as builder

# make the 'app' folder the current working directory
WORKDIR /app
ARG CALC1_URL
ARG CALC2_URL
ARG STORAGE_URL
# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build


FROM nginx:alpine
COPY ./.nginx/nginx.conf /etc/nginx/nginx.conf

## Remove default nginx index pagec
RUN rm -rf /usr/share/nginx/html/*

# Copy from the stage 1
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 2000
ENTRYPOINT ["nginx", "-g", "daemon off;"]