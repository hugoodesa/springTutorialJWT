import axios from "axios";

//axios.defaults.headers.common['Authorization'] = `Bearer ` 

export const getProduto = async (produto,token) => {

  console.log(produto,produto.token);

  // const body={
  //   codigoDeBarras:"213654987",
  //   descricao:"GTX 1080ti",
  //   dataCadastro:"12/12/2021",
  //   preco:200.0,
  //   estoque:2,
  //   status:"ATIVO"
  // }

  console.log("Produto : "+JSON.stringify(produto));

  await axios({
    baseURL:"http://localhost:3003/produtos",
    method:"POST",
    headers:{
      Authorization:"Baerer "+produto.token
    },
    data:{
      /* login: "hugoo",
      password: "hugoo" */
      ...produto
    },
  })

  //console.log(resp.data);
};

export const getProdutoByid =async (produto) =>{
  const resp = await axios({
    baseURL:"http://localhost:3003/produtos/20",
    method:"GET",
    headers:{
      Athentication:"Bearer " + produto.token
    }
  }) 

  console.log(resp.data)
}

export const getUser = async (user) => {
  console.log("Logar chamado");

  /* const user = {
    login: "hugoo",
    password: "hugoo",
  }; */

  //console.log(JSON.stringify(user));

  const body = JSON.stringify(user)

  console.log(user);

    const resp = await axios({
      baseURL:"http://localhost:3003/auth",
      method:"POST",
      headers:{
        Authorization:"Baerer "
      },
      data:{
        /* login: "hugoo",
        password: "hugoo" */
        ...user
      }
    })
    console.log(resp.status);
    console.log(resp.data);
    
    //return resp.data
    return resp
  
};
