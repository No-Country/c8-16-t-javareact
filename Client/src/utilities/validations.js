export const validations = (values) => {

    const emailRegEx =  /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
    const passwordRegEx = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[,;$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){8,15}$/;
    const dniRegEx = /^[\d]{1,3}\.?[\d]{3,3}\.?[\d]{3,3}$/

    let errors = {};

    if (!values.name) {
        errors.name = "Ingresá un nombre"
    }

    if (!values.lastname) {
        errors.lastname = "Ingresá un Apellido"
    }

    if (!emailRegEx.test(values.email)) {
        errors.email = "Ingresá un correo electrónico válido"
    }

    if(!values.email) {
        errors.email = "Ingresá un correo electrónico"
    }

    if (!passwordRegEx.test(values.password)) {
        errors.password = "La contraseña debe tener: Mínimo 8 caracteres y máximo 15 - Al menos una letra mayúscula y una letra minúscula - Al menos un dígito - No espacios en blanco - Al menos 1 caracter especial"
    }
    if(!values.password) {
        errors.password = "Ingresá una contraseña"
    }

    if(!values.confirmpassword){
        errors.confirmpassword = 'Recordá confirmar la contraseña'
    }
    
    if (values.password != values.confirmpassword){
        errors.confirmpassword = "Las contraseñas no coinciden"
    }

    if (!values.dni){
        errors.dni = "Ingresá un DNI"
    }

    if (!dniRegEx.test(values.dni)) {
        errors.dni = "DNI Inválido"
    }

    if (!values.birthdate){
        errors.birthdate = "Campo obligatorio"
    }

    return errors;
}