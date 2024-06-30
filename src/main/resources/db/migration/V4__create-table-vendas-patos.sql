create table vendas_patos(

    id BIGINT PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    pato_id BIGINT NOT NULL UNIQUE
    valor_venda INTEGER NOT NULL UNIQUE

);