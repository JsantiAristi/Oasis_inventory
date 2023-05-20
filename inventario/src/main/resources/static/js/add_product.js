const { createApp } = Vue

createApp({
    data() {
        return {
            data: [],
            data_type: [],
            name: "",
            description: "",
            image: "../assets/agregar-producto.png",
            type: "",
            price: "",
            quantity: "",
            permitExtension: /(.PNG|.JPG|.png|.jpg)$/i,

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
                this.data_type = Array.from(new Set(this.data.map(product => product.type)));
            })
            .catch(error => console.log(error))
        },
        changePicture(e){
            if (!this.permitExtension.exec(e.target.files[0].name)) {
                alert("Selecciona solo archivos de imagen")
            }else if (e.target.files[0]) {
                this.image = URL.createObjectURL(e.target.files[0])
            } else {
                this.image = "../assets/agregar-producto.png"
            }
        },
        add_product(){
            axios.post('/api/clients/products',
            {
                "name": this.name,
                "description": this.description,
                "type": this.type,
                "quantity": this.quantity,
                "image": this.image,
                "price": this.price
            })
            .then(res => { Swal.fire({
                icon: 'success',
                text: 'Añadiste un nuevo producto',
                showConfirmButton: false,
                timer: 2000
                }).then(() => window.location.href="/pages/products.html")
            })
            .catch(error => { Swal.fire({
                icon: 'error',
                text: error.response.data   
                })
            })
        }
    }
}).mount('#app')