create table editorial (
    id int primary key auto_increment,
    nombre varchar(200) not null,
    sitio_web varchar(500)
);

insert into editorial
(id,nombre,sitio_web) values
( 1,     
  'Planeta',
  'https://www.planetadelibros.com/'
),
( 2,     
  'Mirahadas',
  'https://www.mirahadas.com/'
),
( 3,     
  'Pre-Textos',   
  'https://www.pre-textos.com/'
),
( 4,     
  'Sexto Piso',   
  'https://sextopiso.es/'
),
( 5,     
  'Nordica',   
  'https://www.nordicalibros.com/'
),
( 6,     
  'Acantilado',   
  'https://www.acantilado.es/'
),
( 7,     
  'La Huerta Grande',   
  'https://www.lahuertagrande.com/'
);

create table libro (
    id int primary key auto_increment,
    nombre varchar(200) not null,
    autor varchar(200),
    descripcion text,
    imagen_url varchar(500),
    editorial_id int not null,
    foreign key (editorial_id) references editorial(id)
);

insert into libro (nombre, autor, descripcion, imagen_url, editorial_id) values
('Cien años de soledad', 'Gabriel García Márquez', 'Una obra maestra del realismo mágico', 'https://example.com/cien-anos.jpg', 1),
('El nombre de la rosa', 'Umberto Eco', 'Una novela histórica llena de misterio', 'https://example.com/nombre-rosa.jpg', 2),
('Rayuela', 'Julio Cortázar', 'Una novela experimental revolucionaria', 'https://example.com/rayuela.jpg', 3),
('Pedro Páramo', 'Juan Rulfo', 'Un clásico de la literatura mexicana', 'https://example.com/pedro-paramo.jpg', 4),
('La casa de los espíritus', 'Isabel Allende', 'Una saga familiar épica', 'https://example.com/casa-espiritus.jpg', 5),
('Ficciones', 'Jorge Luis Borges', 'Cuentos laberínticos y filosóficos', 'https://example.com/ficciones.jpg', 6),
('El túnel', 'Ernesto Sabato', 'Un thriller psicológico argentino', 'https://example.com/tunel.jpg', 7);

ALTER TABLE editorial AUTO_INCREMENT = 100;
