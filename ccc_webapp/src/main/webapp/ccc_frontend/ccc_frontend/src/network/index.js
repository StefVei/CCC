import axios from 'axios';

export const cccClient = axios.create({
  baseURL: 'http://localhost:8080/ccc_webapp/',
  timeout: 1000
});
