CREATE TYPE category_acc_enum AS ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');
CREATE TYPE role_enum AS ENUM ('ROLE_USER', 'ROLE_HOST');

CREATE TABLE country (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         continent VARCHAR(255) NOT NULL
);

CREATE TABLE host (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      country_id BIGINT,
                      CONSTRAINT fk_host_country FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE accommodation (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               category category_acc_enum NOT NULL,
                               num_rooms INTEGER,
                               host_id BIGINT,
                               CONSTRAINT fk_accommodation_host FOREIGN KEY (host_id) REFERENCES host(id)
);

CREATE TABLE availability (
                              id SERIAL PRIMARY KEY,
                              date_from TIMESTAMP NOT NULL,
                              date_to TIMESTAMP NOT NULL,
                              accommodation_id BIGINT NOT NULL,
                              price DOUBLE PRECISION,
                              CONSTRAINT fk_availability_accommodation FOREIGN KEY (accommodation_id) REFERENCES accommodation(id)
);

CREATE TABLE shop_users (
                            username VARCHAR(255) PRIMARY KEY,
                            password VARCHAR(255) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            surname VARCHAR(255) NOT NULL,
                            is_account_non_expired BOOLEAN DEFAULT TRUE,
                            is_account_non_locked BOOLEAN DEFAULT TRUE,
                            is_credentials_non_expired BOOLEAN DEFAULT TRUE,
                            is_enabled BOOLEAN DEFAULT TRUE,
                            role role_enum NOT NULL
);

CREATE TABLE shop_users_temporary_reservations (
                                                   shop_users_username VARCHAR(255) NOT NULL,
                                                   temporary_reservations_id BIGINT NOT NULL,
                                                   PRIMARY KEY (shop_users_username, temporary_reservations_id),
                                                   FOREIGN KEY (shop_users_username) REFERENCES shop_users(username),
                                                   FOREIGN KEY (temporary_reservations_id) REFERENCES accommodation(id)
);

select * from country