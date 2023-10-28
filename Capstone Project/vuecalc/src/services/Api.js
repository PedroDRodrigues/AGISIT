import axios from 'axios'
export default (service) => {
    if (service == 'calc1') {
        return axios.create({
            baseURL: '/api/calc1',
            withCredentials: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }
    else if (service == 'calc2') {
        return axios.create({
            baseURL: '/api/calc2',
            withCredentials: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }
    else if (service == 'storage') {
        return axios.create({
            baseURL: '/api/storage',
            withCredentials: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }
}