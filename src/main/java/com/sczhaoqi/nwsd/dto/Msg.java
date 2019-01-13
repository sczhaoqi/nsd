package com.sczhaoqi.nwsd.dto;

import java.io.Serializable;

public class Msg<T>
        implements Serializable
{
    private int code;
    private String message;
    private T data;

    public static class Builder<T>
    {
        private int code;
        private String message;
        private T data;

        public int getCode()
        {
            return code;
        }

        public void setCode(int code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public T getData()
        {
            return data;
        }

        public void setData(T data)
        {
            this.data = data;
        }

        public Builder<T> code(int code)
        {
            this.code = code;
            return this;
        }

        public Builder<T> data(T data)
        {
            this.data = data;
            return this;
        }

        public Builder<T> message(String message)
        {
            this.message = message;
            return this;
        }

        public Msg<T> build()
        {
            Msg<T> responseDto = new Msg<>();
            responseDto.code = this.code;
            responseDto.data = this.data;
            responseDto.message = this.message;
            return responseDto;
        }
    }

    public static Msg<String> ok(String message)
    {
        return new Builder<String>().code(200).message(message).build();
    }

    public static Msg<String> error(String message)
    {
        return new Builder<String>().code(400).message(message).build();
    }

    public static Msg<String> sysError(String message)
    {
        return new Builder<String>().code(500).message(message).build();
    }

    public static <T> Msg<T> ok(String message, T data)
    {
        return new Builder<T>().code(200).data(data).message(message).build();
    }
}
