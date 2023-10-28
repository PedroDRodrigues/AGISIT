import Api from '@/services/Api'

export default {
    add (a,b) {
        return Api('calc1').get('/add?a='+a+'&b='+b);
    },

    sub (a,b) {
        return Api('calc1').get('/sub?a='+a+'&b='+b);
    }
}