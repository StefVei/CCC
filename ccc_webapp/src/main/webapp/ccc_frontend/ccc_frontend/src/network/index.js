const axios = require('axios');

export const cccClient = axios.create({
  baseURL: 'http://localhost:8080/',
  timeout: 1000
});
