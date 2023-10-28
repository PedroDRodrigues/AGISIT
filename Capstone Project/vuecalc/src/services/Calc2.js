import Api from '@/services/Api'

export default {
    divide (a,b) {
        return Api('calc2').get('/divide?a='+a+'&b='+b);
    },

    multiply (a,b) {
        return Api('calc2').get('/multiply?a='+a+'&b='+b);
    }
}