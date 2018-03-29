insert into jezyk values (default, 'polski');
insert into jezyk values (default, 'angielski');

insert into rola values (default, 'niezarejestrowany', 'uzytkownik nie zarejestrowany');
insert into rola values (default, 'zarejestrowany', 'uzytkownik zarejestrowany');
insert into rola values (default, 'redaktor', 'moze dodawac zestawy slowek do podkategorii, do ktorej otrzymal uprawnienia od administratora, moze edytowac i usuwac zestawy slowek, ktore utworzyl');
insert into rola values (default, 'super redaktor', 'moze edytowac wszystkie zestawy slowek z podkategorii, do ktorej dostal uprawnienia');
insert into rola values (default, 'administrator', 'moze tworzyc/edytowac/usuwac/ukrywac kategorie i podkategorie, moze tworzyc nowe zestawy slowek, moze edytowac/usuwac/ukrywac zestawy slowek niezaleznie od tego, kto jest autorem');

insert into kategoria values (default, 'geografia', 'slowka z dziedziny geografii(panstwa,gory,rzeki,itp..)', 'obrazek');
insert into kategoria values (default, 'miasto', 'slowka z miejsc, ktore znajduja sie w miescie', 'obrazek');

insert into podkategoria values (default, 1, 'panstwa', 'slowka z panstw calego swiata', 'obrazek');
insert into podkategoria values (default, 1, 'rzeki', 'slowka z rzek calego swiata', 'obrazek');
insert into podkategoria values (default, 2, 'sklepy', 'slowka ze sklepow w miescie', 'obrazek');
insert into podkategoria values (default, 2, 'kierunki', 'slowka z kierunow', 'obrazek');

insert into konto values (default, 1, 'Robert', 'Pokrzywniak', 'robert@o2.pl', 'niez', 'niez');
insert into konto values (default, 2, 'Andrzej', 'Kowal', 'andrzej@o2.pl', 'zar', 'zar');
insert into konto values (default, 3, 'Wiktor', 'Przybylowski', 'wiktor@o2.pl', 'redaktor', 'redaktor');
insert into konto values (default, 4, 'Anna', 'Nowakowska', 'anna@o2.pl', 'superredaktor', 'superredaktor');
insert into konto values (default, 5, 'Wiesiu', 'Zbyt', 'wiesiu@o2.pl', 'admin', 'admin');

insert into uprawnienia values (3, 3);
insert into uprawnienia values (4, 4);

insert into zestaw values (default, 5, 1, 2, 1, 'europa', 'Czech;Czechy,Germany;Niemcy,Denmark;Dania,Norway;Norwegia,France;Francja,Sweden;Szwecja,Greece;Grecja', 7, '2017-05-03');


