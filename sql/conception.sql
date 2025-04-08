
create table prevision_credit(
    id int auto_increment primary key,
    libelle varchar(50),
    montant numeric(15,2)
);
 create table prevision_depense(
    id int auto_increment primary key,
    id_credit int,
    montant numeric(15,2),
    date_depense date,
    foreign key (id_credit) references prevision_credit(id)
 );

