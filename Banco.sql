use desafio;
Create table Basquete (
	 jogo int(4) NOT NULL primary key auto_increment,
     placar int(4) NOT NULL,
     pts_max int(4) NOT NULL,
     pts_min int(4) NOT NULL,
     pts_recMax int(4) NOT NULL,
     pts_recMin int(4) NOT NULL
);
select * from Basquete;