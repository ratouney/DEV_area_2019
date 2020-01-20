import { HttpException, Logger } from "@nestjs/common";

export class ClassValidateException extends HttpException {
    constructor(err, code = 667) {
        Logger.log(err, "ClassValidateException");
        /*
        super({
            status: code,
            message: "Hello there",
            errors: ["ding", "dong"],
        }, code);
        */
       super("hello there", code);
    }
}