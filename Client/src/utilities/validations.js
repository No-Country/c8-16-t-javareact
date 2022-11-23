export const validations = (values) => {

    const emailRegEx =  /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/

    let errors = {};

    if (!values.name) {
        errors.name = "Ingrese un nombre"
    }

    if (!values.lastName) {
        errors.lastname = "Ingrese un Apellido"
    }

    if (!emailRegEx.test(values.email)) {
        errors.email = "Ingrese un correo electrónico válido"
    }

    if(!values.email) {
        errors.email = "Ingrese un correo electrónico"
    }

    return errors;
}