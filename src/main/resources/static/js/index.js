function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
            $('#blah').attr('src', e.target.result).height(200)
        }

        reader.readAsDataURL(input.files[0])
    }
}

const toggleFiltrosPaquetes = () => {
    const filtros = document.getElementsByClassName('seccionFiltros');
    Array.from(filtros).forEach(filtro => {
        if (filtro.classList.contains('d-none')) {
            filtro.classList.remove('d-none');
            filtro.classList.add('d-block');
        } else {
            filtro.classList.remove('d-block');
            filtro.classList.add('d-none');
        }
    });
}

document.querySelector('.btn-filtros').addEventListener('click', toggleFiltrosPaquetes);