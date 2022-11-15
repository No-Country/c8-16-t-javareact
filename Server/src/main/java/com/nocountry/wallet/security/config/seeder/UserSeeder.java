package com.nocountry.wallet.security.config.seeder;

import com.nocountry.wallet.models.entity.*;
import com.nocountry.wallet.repository.*;
import com.nocountry.wallet.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.System.Logger.Level;
import java.sql.Timestamp;
import java.util.Set;



@Component
public class UserSeeder implements CommandLineRunner {


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;


    private static final System.Logger LOGGER = System.getLogger("Mi log");

    @Override
    public void run(String... args) throws Exception {
        LOGGER.log(Level.INFO, "Inicializing seeder");
        this.loadSeedersUsers();

    }

    private void loadSeedersUsers() {
        if (roleRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()) {

            // Create Roles
            roleRepository.save(new RoleEntity(RoleEnum.USER.getFullRoleName(), null, new Timestamp(System.currentTimeMillis())));
            roleRepository.save(new RoleEntity(RoleEnum.ADMIN.getFullRoleName(), null, new Timestamp(System.currentTimeMillis())));

            Set<RoleEntity> roleAdmin = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
            Set<RoleEntity> roleUser = roleRepository.findByName(RoleEnum.USER.getFullRoleName());

            // Create Users
            this.userRepository.save(new UserEntity("Marta", "Sanchez", "marta-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Guillermo", "Pintos", "guillermo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Sara", "Silva", "sara-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Fabian", "Mendoza", "fabian-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Esteban", "Pinto", "esteban-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Erica", "Strumbergert", "erica-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Florencia", "Bianqueri", "florencia-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Pablo", "Silva", "pablo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Eddard", "Stark", "eddard-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Jon", "Nieve", "jon-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));

            this.userRepository.save(new UserEntity("Gisel", "Zoto", "gisel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ruth", "Solis", "ruth-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Fabian", "vena", "fabi-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Mayra", "Zinko", "mayra-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Diana", "Di Stefano", "diana-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ariel", "Alvarez", "ariel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Claudio", "Matuk", "claudio-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Elton", "John", "elton-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Tyrion", "Lannister", "tyrion-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Daenerys", "Targaryen", "daenerys-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
        }
    }


}
