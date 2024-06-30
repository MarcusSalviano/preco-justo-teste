create table vendas_patos(

    id SERIAL PRIMARY KEY,
    venda_id INTEGER NOT NULL,
    pato_id INTEGER NOT NULL UNIQUE

);