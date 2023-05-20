const { createApp } = Vue

createApp({
    data() {
        return {
            data: [],
            dataFilter: [],
            name: "",
            description: "",
            quantity: "",
            price: "",
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
        changeData(id,tipo){
            this.dataFilter = this.data.filter( product => product.id==id)[0]
            this.name = this.dataFilter.name;
            this.description = this.dataFilter.description;
            this.quantity = this.dataFilter.quantity
            this.price = this.dataFilter.price
            Swal.fire({
                title: 'Modifica el contenido',
                input: 'text',
                showCancelButton: true,
                confirmButtonText: 'Edit',
                showLoaderOnConfirm: true,
                preConfirm: (edit) => {
                    if( this.name === tipo ){
                        this.name = edit;
                    } else if ( this.description === tipo ){
                        this.description = edit;
                    } else if( this.quantity === tipo ){
                        this.quantity = edit;
                    } else if ( this.price === tipo ){
                        this.price = edit;
                    }
                    axios.put('/api/clients/products',
            {
                "id": this.dataFilter.id,
                "name": this.name,
                "description": this.description,
                "type": this.dataFilter.type,
                "quantity": this.quantity,
                "image": this.dataFilter.image,
                "price": this.price
            })
            .then(res => { Swal.fire({
                icon: 'success',
                text: 'Cambiaste correctamente el producto ' + this.name,
                showConfirmButton: false,
                timer: 2000
                }).then(() => window.location.href="/pages/change_product.html")
            })
            .catch(error => { Swal.fire({
                icon: 'error',
                text: error.response.data   
                })
            })
                },
                allowOutsideClick: () => !Swal.isLoading()
            })
        }
    }
}).mount('#app')