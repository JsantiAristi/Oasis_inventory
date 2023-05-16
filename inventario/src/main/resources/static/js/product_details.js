const { createApp } = Vue

createApp({
    data() {
        return {
            data: [],
            id : new URLSearchParams(location.search).get("id"),
        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
            console.log(this.id);
            axios.get('/api/clients/products/' + this.id)
            .then(res => {
                this.data = res.data
                console.log(this.data);
            })
            .catch(error => console.log(error))
        },
    }
}).mount('#app')