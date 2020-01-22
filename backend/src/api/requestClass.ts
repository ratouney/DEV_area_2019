import axios from "axios"

export class Request {
    constructor() {}

    /**
     * Async function which make a get call using axios with custom header
     * @param {header} header : Url we want to call
     */
    async callWithHeader(header) {
        try {
            const resp = await axios(header)
            const data = resp.data
            return (data)
        } catch (err) {
            console.log(err)
        }
    }

    /**
     * Async function which make a get call using axios
     * @param {string} url : Url we want to call
     */
    async getCall(url : string) {
        try {
            const resp = await axios.get(url)
            const data = resp.data
            return (data)
        } catch (err) {
            console.log(err)
        }
    }

    /**
     * Async function which make a put call using axios
     * @param {string} url : Url we want to call
     */
    async putCall(url : string) {
        try {
            const resp = await axios.put(url)
            const data = resp.data
            return (data)
        } catch (err) {
            console.log(err)
        }
    }
}
