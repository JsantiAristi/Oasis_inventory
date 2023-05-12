const { createApp } = Vue

createApp({
    data() {
        return {
            data: [],
            selected_type: "",
            data_filter: [],
            text_input: "",
        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
            axios.get('/api/clients/products')
            .then(res => {
                this.data = res.data
                this.data_filter = this.data
                console.log(this.data);
                this.data_type = Array.from(new Set(this.data.map(product => product.type)));
            })
            .catch(error => console.log(error))
        },
        type_filter(product){
            return product.type.includes(this.selected_type);
        },
        text_filter(product){
            return product.name.toLowerCase().includes(this.text_input.toLowerCase());
        },
        type_text_filter(){
            this.data_filter = this.data.filter( product => {
                return ( this.text_filter(product) && (this.type_filter(product) || this.selected_type.length === 0 ))
            })
        }
    }
}).mount('#app')