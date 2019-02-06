import axios from 'axios';

const url = 'http://localhost:8080';

export const getAll = async () => {
  const response = await axios.get(url + '/users');
  return response.data;
};

export const get = async id => {
  const response = await axios.get(url + '/users/'+id);
  return response.data;
};

export const update = async (id, user) => {
  const response = await axios.put(url + '/users/'+id, user);
  return response.data;
};

export const save = async (user) => {
  const response = await axios.post(url + '/users/', user);
  return response.data;
};

export const exclude = async id => {
  const response = await axios.delete(url + '/users/'+id);
  return response.data;
};
