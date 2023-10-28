import Api from '@/services/Api'

export default {
    getOperations () {
        return Api('storage').get('/operations');
    }
}