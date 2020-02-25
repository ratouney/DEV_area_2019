FROM node:12.13-alpine

WORKDIR /usr/src/app

COPY . .

RUN npm -g install nest
RUN npm install

EXPOSE 3001
CMD ["npm", "start"]