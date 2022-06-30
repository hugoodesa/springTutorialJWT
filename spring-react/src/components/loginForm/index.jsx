import React, { useRef, useState } from "react";
import Toastify from "toastify-js";
import "toastify-js/src/toastify.css";
import { getProduto, getUser, logarRest } from "../../connection";
import { FormProduto } from "../produtoForm";

export const getToken =() => {
  const token = window.localStorage.getItem("token");
  if (token) {
    return JSON.parse(token);
  } else {
    return null;
  }
}

export const FormLogin = () => {
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [token, setToken] = useState({ token: "", visible: false });



  const toastAlert = (message, style) => {
    Toastify({
      text: message,
      duration: 3000,
      style: {
        background: style
          ? style.background
          : "linear-gradient(to right, #00b09b, #96c93d)",
      },
    }).showToast();
  };

  const handleForm = async () => {
    console.log(login, senha);

    try {
      //const token = await getUser({ login, password: senha });

      //
      const { data } = await getUser({ login, password: senha });
      const { token } = data;
      //

      const newStateToken = { token:token, visible: false };
      setToken({...newStateToken});
      //handleCopyButton(newStateToken)
      //toastAlert("token copy successfully");

      const jsonParsed= JSON.stringify(token)
      console.log(jsonParsed);

      window.localStorage.setItem("token", jsonParsed);
    } catch (err) {
      console.log("Erro chamado :" + err);
      toastAlert("user or password incorrect", { background: "red" });
    }
  };

  const handleCopyButton = ({ token }) => {
    navigator.clipboard.writeText(token);
    toastAlert("token copied successfully");
  };

  const isTokenAlreadyGenerated = () => {
    const isTokenGenered = token.token.length > 0;
    if (isTokenGenered) handleCopyButton(token);
    return isTokenGenered;
  };

  const copyToken = () => {
    if (isTokenAlreadyGenerated()) return;

    //const tokenLocalStorage = window.localStorage.getItem("token");
    //console.log(JSON.parse(token))
    //const tokenParsed = JSON.parse(tokenLocalStorage);

    // const newStateToke = { token: token.token, visible: true };
    // setToken(newStateToke);

    handleCopyButton(token);
  };


  return (
    <>
      <div
        style={{
          width: "100%",
          display: "flex",
          justifyContent: "center",
          flexDirection: "row",
        }}
      >
        <div style={{ width: "25%" }}>
          <div className="mb-3">
            <label htmlFor="exampleInputEmail1" className="form-label">
              Email address
            </label>
            <input
              type="email"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              value={login}
              onChange={e => setLogin(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="exampleInputPassword1" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="exampleInputPassword1"
              value={senha}
              onChange={e => setSenha(e.target.value)}
            />
          </div>

          <button
            style={{ width: "100%" }}
            type="submit"
            onClick={() => {
              handleForm()
              copyToken()
            }}
            className="btn btn-primary"
            disabled={token.token.length > 0}
          >
            Submit
          </button>

          {token.token.length > 0 && (
            <>
              {/* <button
                style={{ width: "100%", marginTop: "10px" }}
                type="submit"
                onClick={() => copyToken()}
                className="btn btn-primary"
              >
                GetToken
              </button> */}
              <FormProduto/>
            </>
          )}
        </div>
      </div>
    </>
  );
};
