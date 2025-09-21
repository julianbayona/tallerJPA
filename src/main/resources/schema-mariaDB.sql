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
( 6,     
  'Acantilado',   
  'https://www.acantilado.es/'
),
( 7,     
  'La Huerta Grande',   
  'https://www.lahuertagrande.com/'
);

alter table libro
add column editorial_id int,
add foreign key (editorial_id) references editorial(id);

update libro set editorial_id = 1 where id in (1, 5);
update libro set editorial_id = 2 where id in (2);
update libro set editorial_id = 3 where id in (3);
update libro set editorial_id = 4 where id in (4);
update libro set editorial_id = 5 where id in (6);
update libro set editorial_id = 6 where id in (7);
update libro set editorial_id = 7 where id in (8);

alter table libro
modify editorial_id int not null;
