const { createApp } = Vue

createApp({
    data() {
        return {
            data: [],

        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
            axios.get('/api/clients/products')
            .then(res => {
                this.data = res.data;
                console.log(this.data);
            })
            .catch(error => console.log(error))
        },
        changeData(){
            Swal.fire({
                title: 'Modifica el contenido',
                input: 'text',
                showCancelButton: true,
                confirmButtonText: 'Edit',
                showLoaderOnConfirm: true,
                preConfirm: (edit) => {
                    console.log(edit);
                },
                allowOutsideClick: () => !Swal.isLoading()
            })
        }
    }
}).mount('#app')