local isExist = redis.call('exists', KEYS[1])
    if isExist == 1 then
        local nums = redis.call('get', KEYS[1])
        if nums > "0" then
            redis.call('decr', KEYS[1])
            return "success"
        else
            return "fail"
        end
    else
        return "notfound"
end