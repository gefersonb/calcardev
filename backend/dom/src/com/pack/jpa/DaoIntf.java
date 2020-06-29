package com.pack.jpa;

public interface DaoIntf<T extends BaseEntity<?>> {
	
	public T salvar(T entity); 
	public void excluir(T entity);
	public T atualizar(T entity);

}
