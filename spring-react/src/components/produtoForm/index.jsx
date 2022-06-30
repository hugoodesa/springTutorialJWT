import React, { useEffect, useState } from "react";
import { getProduto, getProdutoByid } from "../../connection";
import { getToken } from "../loginForm";

export const FormProduto = ({props}) => {

  const defaultState={
    descricao:"",
    codigoDeBarras:"",
    dataCadastro:"",
    preco:"",
    estoque:"",
    status:""
  }

  const [produto, setProduto] = useState(defaultState)

  const handleInput =(e)=>{
   const chave = e.target.id
   let valor = e.target.value

   if(Number(valor)>=0){
    valor=Number(valor)
   }

   setValueToProduto(chave,valor)
  }

  const setValueToProduto=(key,value)=>{
    produto[key]=value
  }

  const handleForm =() => {
    // console.log(produto)
    // const {token} = getToken()
    // console.log(produto, token)

    // getProduto(produto, token)

    //getProdutoByid(produto)
    getProduto(produto)
  }

  

  return (
    <div >
      <div >
        <div className="mb-3">
          <label htmlFor="exampleInputEmail1" className="div-label">
            Descricao
          </label>
          <input onChange={e=>handleInput(e)} type="text" className="form-control" id="descricao" />
        </div>

        <div className="mb-3">
          <label htmlFor="codigoDeBarras" className="div-label">
            Código de barras
          </label>
          <input onChange={e=>handleInput(e)} type="number" className="form-control" id="codigoDeBarras" />
        </div>

        <div className="mb-3">
          <label htmlFor="dataCadastro" className="div-label">
            dataCadastro
          </label>
          <input onChange={e=>handleInput(e)} type="text" className="form-control" id="dataCadastro" />
        </div>

        <div className="mb-3">
          <label htmlFor="preco" className="div-label">
            preco
          </label>
          <input onChange={e=>handleInput(e)} type="number" className="form-control" id="preco" />
        </div>

        <div className="mb-3">
          <label htmlFor="estoque" className="div-label">
            estoque
          </label>
          <input onChange={e=>handleInput(e)} type="number" className="form-control" id="estoque" />
        </div>

        <div className="mb-3">
          <label htmlFor="status" className="div-label">
            status
          </label>
          <input onChange={e=>handleInput(e)} type="text" className="form-control" id="status" />
        </div>

        <div className="mb-3">
          <label htmlFor="status" className="div-label">
            Token
          </label>
          <input onChange={e=>handleInput(e)} required type="text" className="form-control" id="token" />
        </div>

        <button onClick={handleForm} className="btn btn-primary">Cadastrar</button>
      </div>
    </div>
  );
};
