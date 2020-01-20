import { HttpException, Logger } from "@nestjs/common";

export class MongoException extends HttpException {
    constructor(err, code = 666) {
        let str = err.errmsg;
        Logger.log(err, "GivenError")

        const grp = err.errmsg.match(/E11000.+{ (\w+): \"(.+)\" }/);
        if (grp != null) {
            Logger.log(grp, "Matched");
            str = `Entry [${grp[2]}] already exist for the [${grp[1]}] field`
        } else {
            Logger.log(grp, "Matches ?");
        }

        super(str, code);
    }
}